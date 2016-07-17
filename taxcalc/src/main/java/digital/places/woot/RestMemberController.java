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

	private static final MongoOperations mongoOperation = (MongoOperations)AppContextJavaProvider.getApplicationContext().getBean("mongoTemplate");	

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public ResponseEntity<TaxTemplate> update(@RequestBody TaxTemplate taxTemplate) {

	    if (taxTemplate != null) {
	        System.out.println(taxTemplate);
			Query searchUserQuery = new Query(Criteria.where("index").is(taxTemplate.getIndex()));
	    }

	    // TODO: call persistence layer to update
	    return new ResponseEntity<TaxTemplate>(taxTemplate, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/storeLiteral", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody String jsonString) {

	    if (jsonString != null) {
	        System.out.println(jsonString);
	        mongoOperation.save(jsonString,"form1");
	    }

	    // TODO: call persistence layer to update
	    return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TaxTemplate getRestMembers()
	{
		Query searchUserQuery = new Query(Criteria.where("index").is(-1));
		TaxTemplate taxTemplate = mongoOperation.findOne(searchUserQuery, TaxTemplate.class,"form1");
		return taxTemplate;
	}

}
