package com.company.CompaniesHouse;

import com.company.CompaniesHouse.dao.CompanyDao;
import com.company.CompaniesHouse.model.Company;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompaniesHouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompaniesHouseApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Autowired
	CompanyDao companyDao;


	@Test
	public void contextLoads() {
	}

	@Before
	public void clear(){

		companyDao.deleteAllCompanies();;
	}


	@Test
	public void testCreateCompany(){

		Company company=new Company();
		company.setName("abc");
		company.setCity("London");
		company.setCountry("UK");

		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);
		ResponseEntity<Company> response = restTemplate.exchange(
				createURL("/companies/create"),
				HttpMethod.POST, entity, Company.class);
		String companyId=response.getBody().getId();
		Assert.assertNotNull(companyId);

	}


	@Test
	public void testListCompaniesWhenDatabaseIsNotEmpty(){


		Company company=new Company();
		company.setName("xyz");
		company.setAddress("belmont road");
		company.setCity("London");
		company.setCountry("UK");
		createCompany(company);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<List> response = restTemplate.exchange(
				createURL("/companies/list"),
				HttpMethod.GET, entity, List.class);

		Assert.assertTrue(response.getBody().size()==1);

	}

	@Test
	public void testGetCompany(){

		Company company=new Company();
		company.setName("apple");
		company.setAddress("high road");
		company.setCity("London");
		company.setCountry("UK");
		createCompany(company);


		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Company> response = restTemplate.exchange(
				createURL("/companies/details/" + company.getName()),
				HttpMethod.GET, entity, Company.class);
		Assert.assertNotNull(response.getBody().getId());

	}

	@Test
	public void testAddBeneficiaryOwners(){

		Company company=new Company();
		company.setName("apple");
		company.setAddress("high road");
		company.setCity("London");
		company.setCountry("UK");

		company=createCompany(company);

		Set<String> owners=new HashSet<>();
		owners.add("Tim");
		owners.add("Steve");

		company.setBeneficialOwners(owners);

		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);
		ResponseEntity<Company> response = restTemplate.exchange(
				createURL("/companies/addowners"),
				HttpMethod.POST, entity, Company.class);

		Assert.assertNotNull(response.getBody());


		HttpEntity<String> newEntity = new HttpEntity<String>(null, headers);
		ResponseEntity<Company> newResponse = restTemplate.exchange(
				createURL("/companies/details/" + company.getName()),
				HttpMethod.GET, newEntity, Company.class);

		Assert.assertTrue(newResponse.getBody().getBeneficialOwners().size()==2);



	}

	@Test
	public void testUpdateCompanyDetails(){

		Company company=new Company();
		company.setName("BEll");
		company.setAddress("conventry road");
		company.setCity("Essex");
		company.setCountry("UK");

		company=createCompany(company);

		company.setAddress("edmond road");

		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);
		ResponseEntity<Company> response = restTemplate.exchange(
				createURL("/companies/update"),
				HttpMethod.POST, entity, Company.class);

		Assert.assertTrue(response.getBody().getAddress().equals("edmond road"));



	}

	private Company createCompany(Company company){

		HttpEntity<Company> entity = new HttpEntity<Company>(company, headers);
		ResponseEntity<Company> response = restTemplate.exchange(
				createURL("/companies/create"),
				HttpMethod.POST, entity, Company.class);

		return response.getBody();
	}


	private String createURL(String uri) {
		return "http://localhost:" + port + uri;
	}



}
