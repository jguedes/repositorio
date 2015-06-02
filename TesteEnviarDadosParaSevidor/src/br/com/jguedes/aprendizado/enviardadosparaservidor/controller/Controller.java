package br.com.jguedes.aprendizado.enviardadosparaservidor.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Controller
 */
/**
 * @author João Guedes 02/06/2015 03:30:23
 *
 */
@WebServlet(description = "Classe responsável por receber os dados das requisições e cuidar da lógica de negócio", urlPatterns = { "/Controller" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Controller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String service;

		if (ServletFileUpload.isMultipartContent(request)) {

			try {
				FileItemIterator itens = ServletFileUpload.class.newInstance()
						.getItemIterator(request);

				while (itens.hasNext()) {

					FileItemStream item = itens.next();

					if (item.isFormField()) {

						service = item.getFieldName();

						System.out.println("service: " + service);

					} else {

						service = item.getFieldName();

						InputStream stream = item.openStream();

						String extensao = "jpg";
						String diretorio = "/mnt/774ce448-05a2-4fc5-a56c-3261efee833f/Desenvolvimento/GitHub/Repositorios/Testes/repositorio/TesteEnviarDadosParaSevidor/WebContent/imagens";
						String nomeDaImagem = "imagem2";

						ManipuladorDeImagemEmDiretorio m = new ManipuladorDeImagemEmDiretorio(
								stream, extensao, diretorio, nomeDaImagem);

						m.gravar();

						response.setContentType("image/jpeg");
						response.getOutputStream().write(m.getEmBinario());
						response.getOutputStream().flush();

					}

				}

			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			System.out.println("Não é");

		}

	}

}
