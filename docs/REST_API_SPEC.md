# Carta Base Springboot REST API Specification
For all applications consuming carta base service, use this as a source of truth reference for all development.

# Base Carta SpringBoot REST Service (To be changed)
## Description
(To be changed)

The primary functionalities of this API includes:
- (to be changed)

## REST Specification
### Request

#### Health Check
- Method: **ALL**
- URI: _/health_, _/_
- Headers:
  - _content-type: application/json;charset=UTF-8_

##### Validation
None

### Response

Spec:
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200

##### Example
```
{
	"status": {
		"server": "OK",
		"db": "N/A",
		"dependencies": []
	}
}
```
