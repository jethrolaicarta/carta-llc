# Abstraction Layer in LLC Services 

Each LLC service has 3 abstraction layers: **API**, **Service** and **Data**.

# Structure and Naming
- The root package of each LLC service should be __com.carta.llc.**<service name>**__.
- The entry application config class should be  __com.carta.llc.**<service name>**.Application__.
- The global constants should be stored in the class __com.carta.llc.**<service name>**.Constants__.
- All API layer classes should be under __com.carta.llc.**<service name>**.api__ package.
- All Service layer classes should be under __com.carta.llc.**<service name>**.service__ package.
- All Data layer classes should be under __com.carta.llc.**<service name>**.data__ package.  
     

# API Layer
- API layer classes should handle
	- API endpoint/URL to controller class mapping
	- Request parsing, deserialization and validation
	- Response translation

# Service Layer
- Services should be defined as interfaces. 
- Service interface implementation contain most or all of the business logic implementation.
- Service interface implementation may depends on DAO interfaces from Data layer. 
- Service interface implementation may or may not produce outputs in the form of DTOs. 

# Data Layer
- Data model classes are part of data layer. However, model classes should be POJOs other than any extension or implementation of any library. Data model classes must not be annotated as persistence entity as well.  
- Data access objects (DAOs) should be defined as interfaces containing methods returning data models. 
- Data transfer objects (DTOs) should be transient POJOs and can be used as input and output of service methods.  
- DAOs must never be referenced in API layer. 
