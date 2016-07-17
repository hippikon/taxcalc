package digital.places.woot;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import digital.places.root.AppContextJavaProvider;
import digital.places.root.TaxTemplate;

@Controller
public class RestMemberController {

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public ResponseEntity<TaxTemplate> update(@RequestBody TaxTemplate taxTemplate) {

	    if (taxTemplate != null) {
	        System.out.println(taxTemplate);
	    }

	    // TODO: call persistence layer to update
	    return new ResponseEntity<TaxTemplate>(taxTemplate, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TaxTemplate getRestMembers()
	{
		MongoOperations mongoOperation = (MongoOperations)AppContextJavaProvider.getApplicationContext().getBean("mongoTemplate");	
		Query searchUserQuery = new Query(Criteria.where("index").is(-1));
		TaxTemplate taxTemplate = mongoOperation.findOne(searchUserQuery, TaxTemplate.class);
		return taxTemplate;
	}

}
