package homecompany.validator;

import java.util.regex.Pattern;

public class DataValidator {
    public static boolean validateEmployeeData(String[] dataLine) {

        //dataLine
        //0.Id, 1.Surname, 2.Name, 3.MiddleName, 4.Age, 5.Salary, 6.Email, 7.Company
        Pattern pForNames = Pattern.compile("^[A-Z][a-z]*$");
        if ((!pForNames.matcher(dataLine[1]).find()) ||
                (!pForNames.matcher(dataLine[2]).find()) ||
                (!pForNames.matcher(dataLine[3]).find())) {
            return false;
        }

        Pattern pForAge = Pattern.compile("^[1-9][0-9]$");
        if (pForAge.matcher(dataLine[4]).find()) {
            int ageE = Integer.parseInt(dataLine[4]);
            if (ageE < 18 || ageE > 80) {
                return false;
            }
        } else return false;

        Pattern pForSalary = Pattern.compile("^[0-9]*[.]?[0-9]*$");
        if (!pForSalary.matcher(dataLine[5]).find()) {
            return false;
        }

        //dot and hyphen
        Pattern pForEmail1 = Pattern.compile("^[A-Z0-9]+[A-Z0-9.-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        // /([.-])\1/ - повтор символов
        Pattern pForEmail2 = Pattern.compile("([.-])\\1");
        if (pForEmail1.matcher(dataLine[6]).find()) {
            if (pForEmail2.matcher(dataLine[6]).find()) {
                return false;
            }
        } else return false;

        return true;
    }
}
