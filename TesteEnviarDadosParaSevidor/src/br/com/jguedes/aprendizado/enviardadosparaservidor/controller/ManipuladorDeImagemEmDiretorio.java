/**
 * 
 */
package br.com.jguedes.aprendizado.enviardadosparaservidor.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author João Guedes 02/06/2015 03:59:34
 *
 */
public class ManipuladorDeImagemEmDiretorio {

	private BufferedImage imagem;
	private String extensao;
	private String diretorio;
	private String nomeDeImagem;

	/**
	 * @param imagem
	 * @param extensao
	 * @param diretorio
	 * @param nomeDaImagem
	 */
	public ManipuladorDeImagemEmDiretorio(BufferedImage imagem,
			String extensao, String diretorio, String nomeDaImagem) {
		this.imagem = imagem;
		this.extensao = extensao;
		this.diretorio = diretorio;
		this.nomeDeImagem = nomeDaImagem;
	}

	/**
	 * @param imagem
	 * @param extensao
	 * @param diretorio
	 * @param nomeDaImagem
	 */
	public ManipuladorDeImagemEmDiretorio(Image imagem, String extensao,
			String diretorio, String nomeDaImagem) {
		this(toBufferedImage(imagem), extensao, diretorio, nomeDaImagem);
	}

	/**
	 * @param imagem
	 * @param extensao
	 * @param diretorio
	 * @param nomeDaImagem
	 */
	public ManipuladorDeImagemEmDiretorio(byte[] imagem, String extensao,
			String diretorio, String nomeDaImagem) {
		this(Toolkit.getDefaultToolkit().createImage(imagem), extensao,
				diretorio, nomeDaImagem);
	}

	/**
	 * @param imagem
	 * @param extensao2
	 * @param diretorio2
	 * @param nomeDaImagem
	 */
	public ManipuladorDeImagemEmDiretorio(InputStream imagem, String extensao,
			String diretorio, String nomeDaImagem) {
		this(getDataFromInputStream(imagem), extensao, diretorio, nomeDaImagem);
	}

	public void gravar() {
		try {
			ImageIO.write(imagem, extensao, new File(diretorio + "/"
					+ nomeDeImagem + "." + extensao));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return <br>
	 *         Fonte:
	 *         http://www.mkyong.com/java/how-to-convert-bufferedimage-to-
	 *         byte-in-java/ <br>
	 *         Acessado em: 2 jun.2015
	 */
	public byte[] getEmBinario() {

		byte[] imagemEmBytes = null;

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			ImageIO.write(imagem, extensao, outputStream);

			outputStream.flush();

			imagemEmBytes = outputStream.toByteArray();

			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return imagemEmBytes;

	}

	/**
	 * @param stream
	 * @return
	 * @throws IOException
	 * 
	 * <br>
	 *             Fonte:
	 *             https://github.com/ikai/DateEngine/blob/master/src/com/
	 *             dateengine/controllers/PhotoServlet.java<br>
	 *             Acessado em: 2 jun.2015
	 */
	private static byte[] getDataFromInputStream(InputStream stream) {

		byte[] rawData = null;
		int len;
		byte[] buffer = new byte[8192];
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			while ((len = stream.read(buffer, 0, buffer.length)) != -1)
				output.write(buffer, 0, len);
			rawData = output.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rawData;
	}

	/**
	 * @param image
	 * @return
	 * 
	 * <br>
	 *         Fonte: http://lm2a.googlecode.com/svn/trunk/Waxuka/src/java/
	 *         com/lm2a/svr/ImageServlet.java <br>
	 *         Acessado em: 2 jun.2015
	 */
	private static BufferedImage toBufferedImage(Image image) {
		BufferedImage bufferedImage = null;
		if (image != null) {
			/** miramos uqe la imagen no sea ya una instancia de BufferedImage */

			if (image instanceof BufferedImage) {

				/** genial, no hay que hacer nada */

				return ((BufferedImage) image);

			} else {

				/** nos aseguramos que la imagen estÃ¡ totalmente cargada */

				image = new ImageIcon(image).getImage();

				/** creamos la nueva imagen */

				bufferedImage = new BufferedImage(

				image.getWidth(null),

				image.getHeight(null),

				BufferedImage.TYPE_INT_RGB);

				Graphics g = bufferedImage.createGraphics();

				g.drawImage(image, 0, 0, null);

				g.dispose();

			}

		}
		return (bufferedImage);
	}

	/**
	 * @return the imagem
	 */
	public BufferedImage getImagem() {
		return imagem;
	}

	/**
	 * @param imagem
	 *            the imagem to set
	 */
	public void setImagem(BufferedImage imagem) {
		this.imagem = imagem;
	}

	/**
	 * @return the extensao
	 */
	public String getExtensao() {
		return extensao;
	}

	/**
	 * @param extensao
	 *            the extensao to set
	 */
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	/**
	 * @return the diretorio
	 */
	public String getDiretorio() {
		return diretorio;
	}

	/**
	 * @param diretorio
	 *            the diretorio to set
	 */
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	/**
	 * @return the nomeDeImagem
	 */
	public String getNomeDeImagem() {
		return nomeDeImagem;
	}

	/**
	 * @param nomeDeImagem
	 *            the nomeDeImagem to set
	 */
	public void setNomeDeImagem(String nomeDeImagem) {
		this.nomeDeImagem = nomeDeImagem;
	}

}
