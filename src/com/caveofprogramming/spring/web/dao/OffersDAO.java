package com.caveofprogramming.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("offersDao")
public class OffersDAO {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public Offer getOffer(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		//return jdbc.query("SELECT * FROM offers WHERE id in (1,2,3,4) and name = 'Sue' ORDER BY name", params, new RowMapper<Offer>() {
		return jdbc.queryForObject("SELECT * FROM offers WHERE id = :id ORDER BY name", params, new RowMapper<Offer>() {
			
			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				
				return offer;
			}
			
		});
	}
	
	public boolean update(Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("UPDATE offers SET name=:name, email=:email, text=:text WHERE id=:id", params) == 1;
		//(14, "Claire", "CC@nowhere.com", "updated...")
	}
	
	@Transactional
	public boolean create(Offer offer){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("INSERT INTO offers (id, name, email, text) VALUES (:id, :name, :email, :text)", params) == 1;
		
	}
	
	public int[] create(List<Offer> offers){
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("INSERT INTO offers (name, email, text) VALUES (:name, :email, :text)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("DELETE FROM offers WHERE id = :id", params) == 1;
	}
	
	//public boolean insert(int id, String name, String email, String text) {
	public boolean insert(String name, String email, String text) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		//params.addValue("id", id);
		params.addValue("name", name);
		params.addValue("email", email);
		params.addValue("text", text);
		
		//return jdbc.update("INSERT INTO offers (id, name, email, text) VALUES (:id, :name, :email, :text)", params) == 1;
		return jdbc.update("INSERT INTO offers (name, email, text) VALUES (:name, :email, :text)", params) == 1;
	}
	
	public List<Offer> getOffers() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", "Sue");
	
		
		//return jdbc.query("SELECT * FROM offers WHERE id in (1,2,3,4,5) OR name = :name ORDER BY name", params, new RowMapper<Offer>() {
		return jdbc.query("SELECT * FROM offers ORDER BY name", params, new RowMapper<Offer>() {

			public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
				Offer offer = new Offer();
				
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setText(rs.getString("text"));
				offer.setEmail(rs.getString("email"));
				
				return offer;
			}
			
		});
	}
}
