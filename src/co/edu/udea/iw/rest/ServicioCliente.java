package co.edu.udea.iw.rest;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.iw.bl.impl.ClienteBLImpl;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.ClienteWS;
import co.edu.udea.iw.exception.MyException;

@Path("Cliente")
@Component
public class ServicioCliente {
	
	@Autowired
	private ClienteBLImpl clienteService;
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<ClienteWS> obtener() throws RemoteException{
		List<ClienteWS> clientes=new ArrayList<ClienteWS>();
		
		List<Cliente> listaClientes=null;
		try{
			listaClientes=clienteService.obtener();
			
			for(Cliente cliente : listaClientes){
				ClienteWS clienteWS=new ClienteWS();
				clienteWS.setCedula(cliente.getCedula());
				clienteWS.setApellidos(cliente.getApellidos());
				clienteWS.setEmail(cliente.getEmail());
				clienteWS.setNombres(cliente.getNombres());
				
				clientes.add(clienteWS);
				
			}
		}catch(MyException e){
			e.getMessage();					
		}
		return clientes;
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String guardarCliente(@QueryParam("cedula") String cedula,@QueryParam("nombres") String nombres,
			@QueryParam("apellidos") String apellidos, @QueryParam("email") String email,
			@QueryParam("usuario") String usuario)throws MyException{
		
		try{
			clienteService.guardar(cedula, nombres, apellidos, email, usuario);
		}catch(MyException e){
			return e.getMessage();
		}
		
		return "";
	}
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	public String actualizarCliente(@QueryParam("cedula") String cedula,@QueryParam("nombres") String nombres,
			@QueryParam("apellidos") String apellidos, @QueryParam("email") String email,
			@QueryParam("usuario") String usuario)throws MyException{
		
		try{
			clienteService.actualizarCliente(cedula, nombres, apellidos, email, usuario);
		}catch(MyException e){
			return e.getMessage();
		}
		
		return "";
	}
	
	
	@Produces(MediaType.TEXT_PLAIN)
	@PUT
	public String eliminarCliente(@QueryParam("cedula") String cedula, 
			@QueryParam("usuario") String usuario)throws MyException{
		
		try{
			clienteService.eliminarCliente(cedula, usuario);
		}catch(MyException e){
			return e.getMessage();
		}
		
		return "";
	}

}
