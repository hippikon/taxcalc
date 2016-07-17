package digital.places.woot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/members", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Members getRestMembers()
	{
		Members members = new Members();
		members.setMembers(findAll(10));
		return members;
	}

	@RequestMapping(value = "/members100", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Members getRestMembers100()
	{
		Members members = new Members();
		members.setMembers(findAll(100));
		return members;
		
	}

	@RequestMapping(value = "/members10000", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Members getRestMembers10000()
	{
		Members members = new Members();
		members.setMembers(findAll(10000));
		return members;
		
	}
	
	@RequestMapping(value = "/membersxml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Members getRestMembersxml()
	{
		Members members = new Members();
		members.setMembers(findAll(10));
		return members;
		
	}

	@RequestMapping(value = "/membersxml100", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Members getRestMembersxml100()
	{
		Members members = new Members();
		members.setMembers(findAll(100));
		return members;
		
	}

	@RequestMapping(value = "/membersxml10000", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Members getRestMembersxml10000()
	{
		Members members = new Members();
		members.setMembers(findAll(10000));
		return members;
		
	}

	@RequestMapping(value = "/membersatom", method = RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public @ResponseBody Members getRestMembersatom()
	{
		Members members = new Members();
		members.setMembers(findAll(10));
		return members;
		
	}

	private List<Member> findAll(int range)
	{
		List<Member> membersList = new ArrayList<Member>();
		for (int i=0;i<range;i++)
		{
			Member member = new Member();
			member.setName("Madhuri"+i);
			member.setPhone("123"+i);
			membersList.add(member);
		}
		return membersList;
	}

}
