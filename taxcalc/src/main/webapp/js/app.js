
var app = angular.module("skeep",["ngRoute"]);
	
	app.config(function($routeProvider){
		$routeProvider
		
		.when("/",{
				templateUrl: "views/page2.html",
				controller: "skeepC2"
			}
		)
		.when("/modify",{
				templateUrl: "views/page3.html",
				controller: "modifyForm"
			}
		)
		.otherwise({
				redirectTo: "/"
			}
		)
		
	});

	app.controller
	("skeepC1",
		["$scope",function($scope)
			{
				$scope.appTitle = "Tax Calculator";
			}
		]
	);

	app.service
	("dataservice",function($http)
		{
			var dataserviceobject = {};
			dataserviceobject.save = function(input)
			{
				var entry = eval("["+input+"]");
				
				entry.index = dataserviceobject.getNewId();
			
				var tax = dataserviceobject.calculateTax(entry);
				
				dataserviceobject.setrefundvsowed(tax,entry);
				
				$http.post("/taxcalc/o/storeLiteral",entry)
				.then(
					function(data){
						//dataserviceobject.counter = data.data.id;
						console.log(data.data);
					},
					function(data,status){
						alert("Error occured "+status);
					}
				);

			}		
			dataserviceobject.counter = 3;
			
			dataserviceobject.getNewId = function()
			{
				return dataserviceobject.counter++;
			}


			dataserviceobject.calc = function(entry)
			{
				var tax = dataserviceobject.calculateTax(entry);
				
				dataserviceobject.setrefundvsowed(tax,entry);
				
			}		
			
			
			dataserviceobject.calculateTax = function(entry)
			{
				var formula = entry.taxFormula;
				console.log(formula);
				var tax = eval(dataserviceobject.transform(formula,"entry"));
					//.1*(entry.wages+entry.interest);
				return tax;
			}

			dataserviceobject.transform = function(formula,prefix)
			{
				formula = formula.replace("wages",prefix+".wages");
				formula = formula.replace("interest",prefix+".interest");
				return formula;
			}
			
			dataserviceobject.setrefundvsowed = function(tax,entry)
			{
				if (tax < entry.paid)
				{
					entry.refund = entry.paid - tax;	
					entry.owed = 0;
				}
				else
				{
					entry.refund = 0;
					entry.owed = tax - entry.paid;
				}
			}
			
			return dataserviceobject;	
		}
	);
	
	app.controller
	("skeepC2",
		["$scope","$location","dataservice","$http",function($scope,$location,dataservice,$http)
			{
				$scope.items = dataservice.items;
				$scope.fitems = dataservice.fields;
				$scope.newItem = {};
				//$scope.newItem = {index:-1,firstName:"Abc", lastName:"123", wages:1000.00,interest:100.00,paid:300.00,refund:0.00,owed:0.00,taxFormula:"0.1*(wages+interest)"};
				$http.get("/taxcalc/o/form")
				.then(function(response) {
					$scope.newItem = response.data;
					console.log($scope.newItem);
				});
				$scope.calculate = function()
				{
					dataservice.calc($scope.newItem);
				}
				console.log(angular.element(document.body).injector().get('dataservice'));
			}
		]
	);

	app.controller
	("modifyForm",
		["$scope","$location","dataservice","$http",function($scope,$location,dataservice,$http)
			{
				$scope.newForm = '{index:-1,firstName:"Abc", lastName:"123", wages:1000.00,interest:100.00,paid:300.00,refund:0.00,owed:0.00,taxFormula:"0.1*(wages+interest)"}';
				$scope.addField = function()
				{
					dataservice.save($scope.newForm);
				}
			}
		]
	);
