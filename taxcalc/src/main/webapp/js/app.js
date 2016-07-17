
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
			dataserviceobject.items = 
				[
					{id:1,firstName:"Madhuri", lastName:"Ramachandran", wages:1000.00,interest:100.00,paid:300.00,refund:0.00,owed:0.00},
					{id:2,firstName:"Sri Vamshi Mohan", lastName:"Darbha", wages:1000.00,interest:100.00,paid:300.00,refund:0.00,owed:0.00}

				];
			dataserviceobject.counter = 3;

			dataserviceobject.fields = 
				[
					{fid:1,value:"firstName"},
					{fid:2,value:"lastName"},
					{fid:3,value:"wages"},
					{fid:4,value:"interest"},
					{fid:5,value:"paid"},
					{fid:6,value:"refund"},
					{fid:7,value:"owed"}

				];
			dataserviceobject.fcounter = 8;

			
			dataserviceobject.save = function(entry)
			{
				entry.id = dataserviceobject.getNewId();
			
				var tax = dataserviceobject.calculateTax(entry);
				
				dataserviceobject.setrefundvsowed(tax,entry);
				
				dataserviceobject.items.push(entry);


				$http.post("/springrest/o/store",entry)
				.then(
					function(data){
						dataserviceobject.counter = data.id;
						console.log(data.data.id);
					},
					function(data,status){
						alert("Error occured "+status);
					}
				);

			}		

			dataserviceobject.addField = function(entry)
			{
				entry.fid = dataserviceobject.getNewFId();
				console.log(entry.fid);
				dataserviceobject.fields.push(entry);
			}	
			
			dataserviceobject.getNewId = function()
			{
				return dataserviceobject.counter++;
			}

			dataserviceobject.getNewFId = function()
			{
				return dataserviceobject.fcounter++;
			}
			
			dataserviceobject.calculateTax = function(entry)
			{
				var tax = .1*(entry.wages+entry.interest);
				console.log(tax);
				return tax;
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
		["$scope","$location","dataservice",function($scope,$location,dataservice)
			{
				$scope.items = dataservice.items;
				$scope.fitems = dataservice.fields;
				$scope.newItem = {id:3,firstName:"Abc", lastName:"123", wages:1000.00,interest:100.00,paid:300.00,refund:0.00,owed:0.00};
				$scope.calculate = function()
				{
					dataservice.save($scope.newItem);
				}
				console.log(angular.element(document.body).injector().get('dataservice'));
			}
		]
	);

	app.controller
	("modifyForm",
		["$scope","$location","dataservice",function($scope,$location,dataservice)
			{
				$scope.fitems = dataservice.fields;
				console.log(angular.element(document.body).injector().get('dataservice'));
				$scope.newItem = {fid:-1,value:"New Item"};
				$scope.addField = function()
				{
					dataservice.addField($scope.newItem);
				}
			}
		]
	);
