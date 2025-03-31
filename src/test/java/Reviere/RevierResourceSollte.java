package Reviere;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

@QuarkusTest
class RevierResourceSollte
{
	@Inject
	EntityManager entityManager;

	@Test
	@TestSecurity(authorizationEnabled = false)
	@DisplayName("alle Reviere liefern.")
	void testGetReviere(){
		String erwartetesJson = "[{\"ansprechpartner\":[\"Ansprechpartner1\"],\"id\":\"c4bb094f-23b1-4c06-9d9c-c496ed0c5e0d\",\"name\":\"Revier1\"},{\"ansprechpartner\":[\"Ansprechpartner2\"],\"id\":\"05224c4e-ca30-4a03-a6fd-32ec3e79b382\",\"name\":\"Revier2\"}]";

		given()
			.when().get("/api/reviere")
			.then()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body("name", hasItems("Revier1", "Revier2"));
	}

	@Test
	@TestTransaction
	@TestSecurity(authorizationEnabled = false)
	@DisplayName("Revier löschen können.")
	void testDeleteRevier() {
		given()
			.when().delete("/api/reviere/cf2fef77-e856-4e19-9137-c009dedb85a8")
			.then()
			.statusCode(204);
	}

	// TODO: Trotz der TestTransaction Annotation wird eine Transaction durchgeführt, die auch noch in die TestMethode testGetReviere strahlt und daher unerwünschte Seiteneffekte erzielt
	@Test
	@TestTransaction
	@TestSecurity(authorizationEnabled = false)
	@DisplayName("Revier anlegen können.")
	void testPostRevier() {
		String revierJson = "{\"name\": \"Neues Revier\",\"ansprechpartner\": [\"string\"]}";

		given()
			.contentType(ContentType.JSON)
			.body(revierJson)
			.when().post("/api/reviere/")
			.then()
			.statusCode(Response.Status.CREATED.getStatusCode());
	}

	// TODO: Test AUTH
}
