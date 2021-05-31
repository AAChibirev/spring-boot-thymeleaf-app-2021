package homecompany.controller;

import homecompany.exception.ResourceNotFoundException;
import homecompany.model.EmailModel;
import homecompany.model.Employee;
import homecompany.repository.EmployeeRepository;
import homecompany.service.FileUploadService;
import homecompany.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private FileUploadService fileUpload;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/writing")
    public String writingSubmit(Model model) {
        model.addAttribute("employee", new Employee());
        return "writing";
    }

    @PostMapping("/writing")
    public String writingSubmit(@ModelAttribute @Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESuLT ERROR");
            return "writing";
        }
        model.addAttribute("emps", employee);
        employeeRepository.save(employee);

        return "writing";
    }

    @GetMapping("/loading")
    public String loadingSubmit() {

        return "loading";
    }

    @PostMapping("/loading")
    public String loadingSubmit(Model model) {

        List<Employee> employees = fileUpload.uploadData();
        model.addAttribute("emps", employees);
        return "loading";
    }

    @GetMapping("/finding")
    public String findingSubmit(Model model) {
        model.addAttribute("employee", new Employee());
        return "finding";
    }

    @PostMapping("/finding")
    public String findingSubmit(@ModelAttribute @Valid Employee employee,
            BindingResult bindingResult,
            Model model, @RequestHeader(value="User-Agent", required=false) String userAgent
            , Model modelMail)
            throws ResourceNotFoundException {
        if(!validateNameAndSurname(employee.getName(), employee.getSurname())) {
            System.out.println("BINDING RESULT ERROR");
            return "finding";
        }

        List<Employee> employees = employeeRepository.findByNameAndSurname(employee.getName(), employee.getSurname());
        if (employees.size() == 0) {
            throw new ResourceNotFoundException("User with name: "
                    + employee.getName()
                    +" and surname: "
                    + employee.getSurname()
                    +" not found");
        }


        model.addAttribute("emps", employees);


        employees.forEach(System.out::println);
        System.out.println(userAgent);
        System.out.println(new Date());

        modelMail.addAttribute("emailTo", new String());

        return "finding";
    }


    @GetMapping("/sending")
    public String sendingSubmit(Model model) {
        model.addAttribute("emailMess", new EmailModel());
        return "sending";
    }

    @PostMapping("/sending")
    public String sendingSubmit(@ModelAttribute("emailMess") @Valid EmailModel emailModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("BAD BINDING");
            return "sending";
        }

        mailSender.send(emailModel.getEmailTo(),emailModel.getSubject(),emailModel.getMessage());

        return "sending";
    }

    private boolean validateNameAndSurname(String name, String surname) {
        Pattern patternForNames = Pattern.compile("^[A-Z][a-z]*$");
        Matcher matcher1 = patternForNames.matcher(name);
        Matcher matcher2 = patternForNames.matcher(surname);

        return  matcher1.find() && matcher2.find();

    }

}
