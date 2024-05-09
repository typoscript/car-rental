package carRental.car.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;

import carRental.car.model.CarDao;
import carRental.car.model.CarResponseDto;

/**
 * Servlet implementation class CarGetAllAction
 */
@WebServlet("/CarGetAllAction")
public class CarGetAllAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarGetAllAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CarDao carDao = CarDao.getInstance();
		
		JSONObject json = new JSONObject();
		JSONObject currentCar = new JSONObject();
		JSONArray carArr = new JSONArray();
		
		for (CarResponseDto car : carDao.findCarAll()) {
			currentCar = new JSONObject();
			currentCar.put("brand", car.getBrand());
			currentCar.put("name", car.getName());
			currentCar.put("type", car.getType());
			currentCar.put("fuelType", car.getFuelType());
			currentCar.put("year", car.getYear());
			currentCar.put("imgUrl", car.getImgUrl());
			currentCar.put("fee", car.getFee());
			currentCar.put("mileage", car.getMileage());
			
			carArr.put(currentCar);
		}

		json.put("cars", carArr);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
