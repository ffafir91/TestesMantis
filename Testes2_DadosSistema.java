package Testes2;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class DadosSistema {

    protected static WebDriver navegador;

    public void abrirTela(String url){
        navegador.get(url);
    }

    //Método para informar o login e senha
    public void acessarSistema (String Usuario, String Senha) {

        navegador.findElement(By.name("username")).sendKeys(Usuario);
        navegador.findElement(By.name("password")).sendKeys(Senha);
        navegador.findElement(By.className("button")).click();

    }

    //Método para capturar a tela atual
    public static void capturarTela(WebDriver webDriver, String fileWithPath) throws Exception{

        TakesScreenshot captura = ((TakesScreenshot)webDriver);

        File arquivoCaptura = captura.getScreenshotAs(OutputType.FILE);

        File arquivoDestino = new File(fileWithPath);

        FileUtils.copyFile (arquivoCaptura, arquivoDestino);
    }
}
