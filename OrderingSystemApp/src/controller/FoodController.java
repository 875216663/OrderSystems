package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Food;
import tools.DatabaseConnector;

public class FoodController implements IFoodController{

	
	@Override
	public void addFood(Food food) {
		try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString = "INSERT INTO food (cuisineId, name, description,imageName,price) VALUES (?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, food.getCuisineId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getDescription());
            preparedStatement.setString(4, food.getImageName()); 
            preparedStatement.setDouble(5, food.getPrice());; 
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
            	System.out.println("success");
            } else {
            	System.out.println("failed");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Food> getFood() {
		List<Food> foodList = new ArrayList<>();
		try (Connection connection = DatabaseConnector.getConnection()) {
            String queryString = "SELECT * FROM food";
            PreparedStatement preparedStatement = connection.prepareStatement(queryString);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	int id = resultSet.getInt("cuisineId");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String imageName = resultSet.getString("imageName");
                double price = resultSet.getDouble("price");
                Food food = new Food(id, name,description,imageName,price);
                foodList.add(food);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return foodList;
	}

	@Override
	public void deleteFood(int[] ids) {
		try (Connection connection = DatabaseConnector.getConnection()) {
			for(int id: ids) {
				String queryString = "DELETE FROM food WHERE food.id = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setInt(1, id);
				preparedStatement.executeUpdate();
				
			}
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Food> getFood(String prefix) {
		List<Food> foodList = new ArrayList<>();
		try (Connection connection = DatabaseConnector.getConnection()) {
			
			String queryString = "SELECT * FROM food WHERE food.name LIKE ?";
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, prefix + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet == null) {
				return null;
			}else {
				while (resultSet.next()) {
	            	int id = resultSet.getInt("id");
	                String name = resultSet.getString("name");
	                String imageName = resultSet.getString("imageName");
	                String description = resultSet.getString("description");
	                double price = resultSet.getDouble(("price"));
	                Food food = new Food(id, name, description, imageName, price);
	                foodList.add(food);
	            }
				return foodList;
			}
              
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

}
