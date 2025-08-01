package JavierIraheta_20230669.JavierIraheta_20230669;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloPooJavierIraheta20230669Application {

	public static void main(String[] args) {
		//Carga variables del .env al sistema
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach( entry ->
						System.setProperty(entry.getKey(),entry.getValue())
		);

		SpringApplication.run(ProyectoModuloPooJavierIraheta20230669Application.class, args);
	}

}
