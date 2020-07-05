package com.mindtree.login;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.mindtree.entity.User;

@Path("/login")
public class LoginApis {

	@POST
	@Path("/performlogin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response validateUser(@FormParam("name") String name, @FormParam("password") String password) {
		try {
			boolean loginSuccess = false;

			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF = con.buildSessionFactory();
			Session session = SF.openSession();

			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("select u from User u");

			List<User> user = (List<User>) query.list();

			for (User user_info : user) {

				if (user_info.getName().equals(name) && user_info.getPassword().equals(password)) {
					System.out.println("Login success");
					loginSuccess = true;
					break;
				}

			}

			session.close();
			SF.close();

			if (!loginSuccess) {
				System.out.println("Login unsuccess");
				return Response.ok("No registered User", MediaType.TEXT_HTML).build();

			}

		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok("User Logined Successfully", MediaType.TEXT_HTML).build();
	}

	@POST
	@Path("/addusers")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response addUser(@FormParam("name") String name, @FormParam("password") String password) {
		try {

			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF = con.buildSessionFactory();
			Session session = SF.openSession();

			User user_info = new User();
			user_info.setName(name);
			user_info.setPassword(password);

			Transaction TR = session.beginTransaction();
			session.save(user_info);
			TR.commit();
			System.out.println("Data added successfully!!!");
			session.close();
			SF.close();
			/*
			 * Statement myStatement = (Statement)
			 * MysqlConnections.getConnections().createStatement(); String query =
			 * " insert into users_login (u_name, u_password) values('"+ name +"', '"+
			 * password+"')"; myStatement.executeUpdate(query);
			 */
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok("User Successfully added", MediaType.TEXT_HTML).build();
	}

	@GET
	@Path("/displayusers")
	@Produces(MediaType.APPLICATION_JSON)
	public String displayUser() {
		String jsonData = new String();

		try {

			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF = con.buildSessionFactory();
			Session session = SF.openSession();

			@SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("select u from User u");

			List<User> user = (List<User>) query.list();

			for (User user_info : user) {
				System.out.println(user_info.getId() + " " + user_info.getName() + " " + user_info.getPassword());
			}

			session.close();
			SF.close();

			Gson gson = new Gson();
			jsonData = gson.toJson(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return jsonData;
	}

	@SuppressWarnings("unchecked")
	@Path("/updateusers")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response updateBook(@FormParam("name") String name, @FormParam("password") String password) {
		boolean successfullUpdate = false;
		String returnMsg = new String();
		try {
			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF = con.buildSessionFactory();
			Session session = SF.openSession();

			Query<User> query = session.createQuery("select u from User u");

			List<User> user = (List<User>) query.list();

			for (User user_info : user) {
				if (user_info.getName().equals(name)) {
					Transaction TR = session.beginTransaction();

					query = session.createQuery("update User u set u.password=:upassword where u.id =:uid");
					query.setParameter("uid", user_info.getId());
					query.setParameter("upassword", password);
					query.executeUpdate();
					TR.commit();
					successfullUpdate = true;
					break;
				}

			}
			if (successfullUpdate)
				returnMsg = "User has been successfully updated with new password";
			else
				returnMsg = "User has been not been updated with new password";

			session.close();
			SF.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok(returnMsg, MediaType.TEXT_HTML).build();
	}

	@SuppressWarnings("unchecked")
	@Path("/deleteusers")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response updateBook(@FormParam("name") String name) {
		boolean successfullDelete = false;
		String returnMsg = new String();
		try {
			Configuration con = new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF = con.buildSessionFactory();
			Session session = SF.openSession();

			Query<User> query = session.createQuery("select u from User u");

			List<User> user = (List<User>) query.list();

			for (User user_info : user) {
				if (user_info.getName().equals(name)) {
					Transaction TR = session.beginTransaction();

					query = session.createQuery("delete from User u where u.id =:uid");
					query.setParameter("uid", user_info.getId());
					query.executeUpdate();
					TR.commit();
					successfullDelete = true;
					break;
				}

			}
			if (successfullDelete)
				returnMsg = "User has been successfully deleted";
			else
				returnMsg = "User has not been been deleted";

			session.close();
			SF.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok(returnMsg, MediaType.TEXT_HTML).build();
	}

}
