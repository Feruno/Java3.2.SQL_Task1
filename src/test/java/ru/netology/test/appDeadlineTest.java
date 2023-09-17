package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.dataTest.DataHelp;
import ru.netology.dataTest.SQLHelp;
import ru.netology.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.dataTest.SQLHelp.cleanDatabase;

public class appDeadlineTest {

    @AfterAll
    static void teardown() {
        //cleanDatabase();
    }

    @Test
    void succesLogin() {
        var login = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelp.getAuthInfo();
        var verification = login.validLogin(authInfo);
        verification.verificationPageVisible();
        var verifCode = SQLHelp.getVerifCode();
        verification.validVerify(verifCode.getCode());
    }

    @Test
    void invalidVerificationCode() {
        var login = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelp.getAuthInfo();
        var verification = login.validLogin(authInfo);
        verification.verificationPageVisible();
        var verifCode = DataHelp.getInvalidVerificationCodeFor();
        verification.verify(verifCode.getCode());
        verification.verifyCodeErrorVisible();
    }

}
