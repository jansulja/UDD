(function(){
	
	angular.module('udd.login')
		.service('LoginService',LoginService);
	
	LoginService.$inject = ['Restangular','$http'];
	function LoginService(Restangular,$http){
		
		this.currentUser ={firstname:undefined,lastname:undefined};
		
		this.updateCurrentUser = function(callback){
			var that = this.currentUser;
			Restangular.one("login/current").get().then(function(user){
				
				if(user){
				  that.firstname = user.firstname;
				  that.lastname = user.lastname;
				  callback(user.firstname,user.lastname);
				 }
			});
						
		}
		
		this.getCurrentUser = function(){
			return this.currentUser;
		}
		
		this.setCurrentUser = function(fname,lname){
			this.currentUser.firstname = fname;
			this.currentUser.lastname = lname;
		}
		
		this.logout = function(success){
			var that = this.currentUser;
			$http({
			    method: 'DELETE',
			    url: 'authenticate',
			})
			.then(function(response) {
				
				that.firstname = undefined;
				that.lastname = undefined;
				success();
			});
		}
		
		this.login = function(user,success,error){
			var that = this.currentUser;
			$http({
			    method: 'POST',
			    url: 'authenticate',
			    data: serializeData(user),
			    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			})
			.then(function(response) {
				
				that.firstname = response.data.firstname;
				that.lastname = response.data.lastname;
				success();
			},function(response) {
				
				error(response.data);
			});			
			
		};
		
		function serializeData( data ) { 
		    // If this is not an object, defer to native stringification.
		    if ( ! angular.isObject( data ) ) { 
		        return( ( data == null ) ? "" : data.toString() ); 
		    }

		    var buffer = [];

		    // Serialize each key in the object.
		    for ( var name in data ) { 
		        if ( ! data.hasOwnProperty( name ) ) { 
		            continue; 
		        }

		        var value = data[ name ];

		        buffer.push(
		            encodeURIComponent( name ) + "=" + encodeURIComponent( ( value == null ) ? "" : value )
		        ); 
		    }

		    // Serialize the buffer and clean it up for transportation.
		    var source = buffer.join( "&" ).replace( /%20/g, "+" ); 
		    return( source ); 
		}
		
		
	}
	
	
})();