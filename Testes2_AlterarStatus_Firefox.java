package Testes2;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class AlterarStatus_Firefox extends DadosSistema {

    public void abrirTela(String url){
        navegador.get(url);
    }

    //Método para abrir a tela do sistema
    @BeforeClass
    public static void abreTela()throws Exception{
        DadosSistema dadosSistema = new DadosSistema();

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\fabiano.pereira\\Drivers\\geckodriver.exe");
        navegador = new FirefoxDriver();
        dadosSistema.abrirTela("https://mantis-prova.base2.com.br/");
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis10.png");
    }

    //Testes de login
    @Test
    public void test7FazerLoginTela() throws Exception {
        acessarSistema("fabiano.fernandes", "Kuj5s6wPdcwz5Js");
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis11.png");
    }

    //Método para validar se o nome do usuário informado está corretamente exibido na tela do sistema
    @Test
    public void test8ValidarNomeUsuario() throws Exception {
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String ValidarNome = navegador.findElement(By.cssSelector(".login-info-left")).findElement(By.cssSelector(".login-info-left > .italic")).getText();
        assertEquals("fabiano.fernandes", ValidarNome);
        this.capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis12.png");
    }

    //Método para alterar dados do projeto (Categoria)
    @Test
    public void test10alterarProjeto () throws Exception{
        navegador.findElement(By.linkText("My View")).click();

        navegador.findElement(By.linkText("0002497")).click();
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis13.png");
        navegador.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Attached Files'])[1]/following::input[3]")).click();
        //navegador.findElement(By.cssSelector("td.center > form > input.button")).click();
        new Select(navegador.findElement(By.name("category_id"))).selectByVisibleText("[All Projects] asasasasa");
        new Select(navegador.findElement(By.name("status"))).selectByVisibleText("confirmed");
        //navegador.findElement(By.name("status")).findElement(By.name("confirmed")).click();
        navegador.findElement(By.name("description")).sendKeys("Descrição do testes para alteração");
        navegador.findElement(By.cssSelector(".button")).click();
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis14.png");

        String newCategoria = navegador.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Desafio jMeter Project 1'])[2]/following::td[1]")).getText();
        assertEquals(newCategoria, "[All Projects] asasasasa");
    }

    //Métod para validar se o projeto selecionado tem ou não categoria
    @Test
    public void test9ErroAlterarProjeto() throws Exception{
        navegador.findElement(By.linkText("0002496")).click();
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis15.png");

        navegador.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Attached Files'])[1]/following::input[7]")).click();
        navegador.findElement(By.name("bugnote_text")).sendKeys("Testes");
        navegador.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='View Status'])[1]/following::input[2]")).click();

        String mensagemErro = navegador.findElement(By.cssSelector("p.center")).getText();
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis16.png");

        String categoria = "\"Category\"" ;
        //String exemplo = "\""+playerName+"\"";
        assertEquals(mensagemErro, "A necessary field "  +categoria+ " was empty. Please recheck your inputs.");

    }

    //Método para sair do sistema pelo botão Logout
    @Test
    public void test11Deslogar () throws Exception{
        navegador.findElement(By.linkText("Logout")).click();
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis17.png");
    }

    //Método para validar se o usuário informado está correto
    @Test
    public void test12ValidarUsuario () throws Exception{
        acessarSistema("fabiano.fernande", "Kuj5s6wPdcwz5Js");
        String erroUsuario = navegador.findElement(By.cssSelector("font")).getText();
        assertEquals(erroUsuario,"Your account may be disabled or blocked or the username/password you entered is incorrect.");
        capturarTela(navegador, "C:\\Users\\fabiano.pereira\\Desktop\\CapturaTestMantis\\TestMantis18.png");
    }

}
