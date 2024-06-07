package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int tryMax = 2;
    @Override
    public boolean retry(ITestResult result) {

        if(count < tryMax) {

            count ++;
            return true;
        }
        return false;
    }
}
