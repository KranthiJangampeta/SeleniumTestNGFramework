package organisation.eCommerce.base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Iretry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 3;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry) {
            count++; // 1
            return true; // testcase will be executed again
        }
        return false; // testcase execution will end
    }
}
