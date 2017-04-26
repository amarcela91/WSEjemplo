package co.edu.udea.iw.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.impl.UsuarioBLImpl;
import co.edu.udea.iw.exception.MyException;

@Path("Usuario")
@Component
public class ServicioUsuario {
	
	UsuarioBLImpl usuarioService;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String validar(String login, String clave) throws MyException{
		try{
			usuarioService.autenticar(login,clave);
		}catch(MyException e){
			return e.getMessage();
		}
		return "";
	}

}
