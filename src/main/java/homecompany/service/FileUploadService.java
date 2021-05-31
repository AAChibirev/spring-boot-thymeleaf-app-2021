package homecompany.service;

import au.com.bytecode.opencsv.CSVReader;
import homecompany.model.Employee;
import homecompany.repository.EmployeeRepository;
import homecompany.validator.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> uploadData() {
        List<Employee> employees = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader("employee.csv"), ',' , '"' , 1);
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    Employee employee = new Employee();
                    //0.Id, 1.Surname, 2.Name, 3.MiddleName, 4.Age, 5.Salary, 6.Email, 7.Company
                    if (DataValidator.validateEmployeeData(nextLine)) {
                        employee.setId(Integer.parseInt(nextLine[0]));
                        employee.setSurname(nextLine[1]);
                        employee.setName(nextLine[2]);
                        employee.setMiddleName(nextLine[3]);
                        employee.setAge(Integer.parseInt(nextLine[4]));
                        employee.setSalary(Double.parseDouble(nextLine[5]));
                        employee.setEmail(nextLine[6]);
                        employee.setCompanyName(nextLine[7]);

                        //save to DB
                        employeeRepository.save(employee);
                        employees.add(employee);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return employees;
    }
}
