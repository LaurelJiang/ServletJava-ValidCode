package com.briup.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 获取动态码
 * @author JT
 *
 */

@WebServlet("/validcode")
public class ValidCodeServlet extends HttpServlet{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D gra = image.createGraphics();
		gra.setColor(Color.WHITE);
		
		//坐标位置
		gra.fillRect(0, 0, 200, 100);
		
		List<Integer> randList = new ArrayList<Integer>();
		Random random = new Random();
		for (int i=0; i<4;i++) {
			randList.add(random.nextInt(10));
		}
		//设置字体
		gra.setFont(new Font("����",Font.ITALIC|Font.BOLD,40));
		Color[] colors=new Color[]{Color.red,Color.YELLOW,Color.BLUE,Color.GREEN,Color.PINK,Color.GRAY};
		for (int i = 0; i < randList.size(); i++) {
			gra.setColor(colors[random.nextInt(colors.length)]);
			gra.drawString(randList.get(i)+"", i*40, 50+(random.nextInt(21)-10));
		}
		
//		gra.setColor(Color.BLACK);
//		for (int i = 0; i < randList.size();i++){
//			gra.drawString(randList.get(i)+"", i*40,50);
//		}
		
		for (int i = 0; i < 2; i++) {
			gra.setColor(colors[random.nextInt(colors.length)]);
			gra.drawLine(0, random.nextInt(100), 200, random.nextInt(101));
		}
		ServletOutputStream outputStream = resp.getOutputStream();
		//工具类
		ImageIO.write(image, "png", outputStream);
		
		HttpSession session = req.getSession();
		session.setAttribute("code", ""+randList.get(0)+randList.get(1)+randList.get(2)+randList.get(3));
	}
}
