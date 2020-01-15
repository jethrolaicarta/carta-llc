# PCAP KYC Service REST API Specification
For all applications consuming KYC service, use this as a source of truth reference for all development.

# Examples
All examples are available via _<kyc base url>/api/rest/examples_.

# verifyIdentity
## Description
The goal of this API endpoint is to perform the verification flow and determine the status of identity verification. It interacts with LexisNexis underneath the hood. We delegate the verification flow and state control to LexisNexis so this API endpoint will behave differently according to the current status of requesting enrollment. LexisNexis will provide verification transaction level state management. However, KYC does keep a track on the verification status on PCB enrollment level. 

The primary functionalities of this API includes:
- obtains initial question set for identity verification
- obtains subsequent question set for identity verification
- submit answers to question set. 
- return permanent states of enrollment. (VERIFIED, PENDING)

## REST Specification
### Request

#### Request For Initial Question Set
- Method: **POST**
- URI: _/api/rest/verifyIdentity_
- Headers:
  - _content-type: application/json;charset=UTF-8_
##### Validation
- enrollmentUuid is required and must be a non-empty string
- _person_ is required but when answers is provided, person might not be needed
- _answers_ is required when submitting answers for previously retrieved questions
- the _person_ and _answers_ are structured intentionally in LexisNexis REST api compatible format to simplify the payload transformation
- note that the type of _Choice_ element is **String** instead of number.
- _person.Addresses[0].Context_, _person.SSN.Type_ and _person.Context_ are not required. KYC will add those properties when any of them are missing. 

##### Example
```
{
	"enrollmentUuid": "f9c6bbde-3ebc-11e9-b210-d663bd873d93",
	"person": {
		"Name": {
			"FirstName": "John",
			"LastName": "Doe"
		},
		"Addresses": [{
			"StreetAddress1": "561 Streetname ST, APT 72",
			"City": "CityName",
			"State": "WI",
			"Zip5": "53195",
			"Country": "US",
			"Context": "primary"
		}],
		"SSN": {
			"Number": "39242####",
			"Type": "ssn9"
		},
		"DateOfBirth": {
			"Year": "1943",
			"Month": "9",
			"Day": "2"
		},
		"Context": "primary"
	}
}
```
#### Submitting Answers
- Method: **POST**
- URI: _/api/rest/verifyIdentity_
- Headers:
  - _content-type: application/json;charset=UTF-8_
##### Validation
- enrollmentUuid is required and must be a non-empty string
- _person_ is required but when answers is provided, person might not be needed
- the _person_ and _answers_ are structured intentionally in LexisNexis REST api compatible format to simplify the payload transformation
- note that the type of _Choice_ element is **String** instead of number.
- _person.Addresses[0].Context_, _person.SSN.Type_ and _person.Context_ are not required. KYC will add those properties when any of them are missing. 

##### Example
```
{
	"enrollmentUuid": "f9c6bbde-3ebc-11e9-b210-d663bd873d93",
	"answers": {
		"QuestionSetId": 2097732,
		"Questions": [{
				"QuestionId": 6245402,
				"Choices": [{
					"Choice": "29483462"
				}]
			},
			{
				"QuestionId": 6245412,
				"Choices": [{
					"Choice": "29483532"
				}]
			},
			{
				"QuestionId": 6245422,
				"Choices": [{
					"Choice": "29483542"
				}]
			}
		]
	},
	"person": {
		"Name": {
			"FirstName": "John",
			"LastName": "Doe"
		},
		"Addresses": [{
			"StreetAddress1": "561 Streetname ST, APT 72",
			"City": "CityName",
			"State": "WI",
			"Zip5": "53195",
			"Country": "US",
			"Context": "primary"
		}],
		"SSN": {
			"Number": "39242####",
			"Type": "ssn9"
		},
		"DateOfBirth": {
			"Year": "1943",
			"Month": "9",
			"Day": "2"
		},
		"Context": "primary"
	}
}
```

### Response
- All response will be in JSON
#### Identity Is Verified

Scenarios: 
- when answers are submitted for initial question set and passed
- when answers are submitted for subsequent question set and passed
- when a verified transaction is found

Spec:
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200

##### Example
```
{
	"status": "VERIFIED"
}
```
#### Identity Is Not Verified. Request For Initial Question Set
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200
##### Example
```
{
	"status": "NOT_VERIFIED",
	"QuestionSet": {
		"QuestionSetId": 2097732,
		"Questions": [{
				"QuestionId": 6245402,
				"Key": 1010,
				"Type": "singlechoice",
				"Text": {
					"Statement": "Which of the following addresses have you ever lived at or been associated with?"
				},
				"HelpText": {
					"Statement": "The addresses listed may be partial, misspelled or contain minor numbering variations from your actual address"
				},
				"Choices": [{
						"ChoiceId": 29483422,
						"Text": {
							"Statement": "128a Woodview Drive"
						}
					},
					{
						"ChoiceId": 29483432,
						"Text": {
							"Statement": "14816 West Arrowhead Lane"
						}
					},
					{
						"ChoiceId": 29483442,
						"Text": {
							"Statement": "6242 Rolling Meadows Drive"
						}
					},
					{
						"ChoiceId": 29483452,
						"Text": {
							"Statement": "8236 25th Court"
						}
					},
					{
						"ChoiceId": 29483462,
						"Text": {
							"Statement": "905 Columbus Street"
						}
					},
					{
						"ChoiceId": 29483472,
						"Text": {
							"Statement": "I have never been associated with any of these addresses"
						}
					}
				]
			},
      # 3 questions in total should be returned
			... 
		]
	}
}
```

#### Answers For Initial Question Set Are Incorrect And The Requester Is Eligible For Subsequent Question Set
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200
##### Example
```
{
	"status": "RETRY",
	"QuestionSet": {
		"QuestionSetId": 2097732,
		"Questions": [{
				"QuestionId": 6245402,
				"Key": 1010,
				"Type": "singlechoice",
				"Text": {
					"Statement": "Which of the following addresses have you ever lived at or been associated with?"
				},
				"HelpText": {
					"Statement": "The addresses listed may be partial, misspelled or contain minor numbering variations from your actual address"
				},
				"Choices": [{
						"ChoiceId": 29483422,
						"Text": {
							"Statement": "128a Woodview Drive"
						}
					},
					{
						"ChoiceId": 29483432,
						"Text": {
							"Statement": "14816 West Arrowhead Lane"
						}
					},
					{
						"ChoiceId": 29483442,
						"Text": {
							"Statement": "6242 Rolling Meadows Drive"
						}
					},
					{
						"ChoiceId": 29483452,
						"Text": {
							"Statement": "8236 25th Court"
						}
					},
					{
						"ChoiceId": 29483462,
						"Text": {
							"Statement": "905 Columbus Street"
						}
					},
					{
						"ChoiceId": 29483472,
						"Text": {
							"Statement": "I have never been associated with any of these addresses"
						}
					}
				]
			},
      #2 questions in total should be returned
			... 
		]
	}
}
```

#### Question Set Is Not Available
Note: 
- _cause_ is for carrying external error message from LexisNexis or other external source or low level source. _cause_ should be used in technical rather than business context.
- _error_ is for KYC specific error message

Scenarios: 
- LexisNexis failed to generate question set

Spec: 
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200


##### Example
```
{
	"status": "PENDING",
  "error":"KYC specific error message",
  "cause": "<error message from LexisNexis or other cause>"
}
```

#### All Attempts Failed For The Day
Note: 
Used up all 3 attempts for the day. 

Spec: 
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200

##### Example
```
{
	"status": "TEMP_LOCKED"
}
```

#### Identity Verification For The Requesting User is Permanently Locked
Scenarios: 
- A locked transaction found in KYC database
- Failed LexisNexis Velocity Check
- Failed All Attempts For Verification


Spec: 
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 200

##### Example
```
{
	"status": "LOCKED"
}
```

#### Missing Required Data In The Request
Spec: 
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 400

##### Example
```
{
	"status": "INSUFFICIENT_INFO",
  "error": "<detailed description>"
}
```

#### Failed For Various Reasons
Note: 
_FAILED_ here means failure to perform verification. For failed identity verification attempts, we will return _TEMP_LOCKED_ or _LOCKED_ instead. 

Spec: 
- Headers:
  - _content-type: application/json;charset=UTF-8_
- Status: 500

##### Example
```
{
	"status": "FAILED", 
  "error" :"LexisNexis is unavailable"
}
```