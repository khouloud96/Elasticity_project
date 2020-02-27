package com.elasticity.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.elasticity.mappers.credentials.ConstraintsSpecification;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class AwsCredentialsParserServiceImpl {

	private String access_key, secret_key;
	private int nb_Worker;
	private Boolean CreationVerif = false;

	private ObjectMapper mapper;
	private ConstraintsSpecification constraintsSpecification;

	// Define a static logger variable so that it references the Logger instance named "AwsCredentialsParserServiceImpl"
	private static Logger logger = Logger.getLogger(AwsCredentialsParserServiceImpl.class);

	public void generateTerraformCredentialsFile(String globalPath) {

		/******************
		 * Extract data (AWS credentials)
		 ******************************/

		mapper = new ObjectMapper();
		try {
			constraintsSpecification = mapper.readValue(new File(globalPath+"awsCredentials.json"), ConstraintsSpecification.class);

			access_key = constraintsSpecification.getAccessCredentials().getAccess_key();
			secret_key = constraintsSpecification.getAccessCredentials().getSecret_key();
			nb_Worker = constraintsSpecification.getInstancesConstraints().getNbWorker();

		} catch (JsonParseException e) {
			logger.error("Exception :: ", e);
		} catch (JsonMappingException e) {
			logger.error("Exception ::", e);
		} catch (IOException e) {
			logger.error("Exception ::", e);
		}

		/******************
		 * Save in Terraform file (terraform.tfvars)
		 ******************************/
		try {
			File terraformCredentialFile = new File(globalPath + "terraform.tfvars");
			System.out.println("\n\n The file 'terraform.tfvars' has been created successfully\n");
			FileWriter writer = new FileWriter(terraformCredentialFile);
			writer.write("access_key = \"" + access_key + "\"\n" + "secret_key =\"" + secret_key + "\"\n"
					+ "nbWorker = \"" + nb_Worker + "\"\n");
			writer.close();
			CreationVerif = true;

		} catch (IOException e) {
			logger.error("Exception Occurred:", e);
		}

	}

	public String getAccess_key() {
		return access_key;
	}

	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public int getNb_Worker() {
		return nb_Worker;
	}

	public void setNb_Worker(int nb_Worker) {
		this.nb_Worker = nb_Worker;
	}

	public Boolean getCreationVerif() {
		return CreationVerif;
	}

	public void setCreationVerif(Boolean creationVerif) {
		CreationVerif = creationVerif;
	}

}