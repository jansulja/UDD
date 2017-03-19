/**
* ResultsService Service
*
* Description
*/
(function(){

	angular.module('udd.ebook.results').service('ResultsService',ResultsService); 

	ResultsService.$inject = ['Restangular'];

	function ResultsService(Restangular) {
		
		this.results = [];

		this.setResults = function(data){
			this.results = data;
		}

		this.getResults = function(){

			results = [
			{
				"ebookId": 1489915691619,
				"title": "Pretraživanje teksta",
				"author": "Dragan Ivanovicdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "03-text-ir_19_03_2017_10_28_11.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 2,
					"name": "Srpski"
				},
				"category": {
					"categoryId": 7,
					"name": "Romance novel"
				},
				"highlight": "text: : jednako se posmatra i <B>dokument</B> koji\r\nnema ni jedan term i <B>dokument</B> koji ima dva terma\r\nDragan ... ",
				"$$hashKey": "object:45"
			},
			{
				"ebookId": 1489915717186,
				"title": "Vektorski model",
				"author": "Dragan Ivanovicdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "05-vec-mod_19_03_2017_10_28_37.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: Ima rangiranja\r\nIma parcijalnog poklapanja upita i dokumenta\r\nI upit i <B>dokument</B> se predstavljaju kao ... ",
				"$$hashKey": "object:46"
			},
			{
				"ebookId": 1489915702324,
				"title": "Bulov model",
				"author": "Dragan Ivanovicdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "04-bool-mod_19_03_2017_10_28_22.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: Konjukcija tri terma: jednako se posmatra i <B>dokument</B> koji\r\nnema ni jedan term i <B>dokument</B> koji ima ... ",
				"$$hashKey": "object:47"
			},
			{
				"ebookId": 1489915731062,
				"title": "Pretraživanje struktuiranih sadržaja",
				"author": "Dragan Ivanovićdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "06-xml-ir_19_03_2017_10_28_51.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: indeksirani\r\nZa razliku od pretrage XML struktuiranih sadrºaja\r\nZna se ²ta je <B>dokument</B> koji se indeksira i ... ",
				"$$hashKey": "object:48"
			},
			{
				"ebookId": 1489915749856,
				"title": "Pretraga veba",
				"author": "Dragan Ivanovicdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "07-web-search_19_03_2017_10_29_09.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: pretraºiva£i\r\nDetekcija pribliºnih duplikata\r\n<B>Dokument</B> se izdeli da delove\r\nili se izdeli na shingles (N ... ",
				"$$hashKey": "object:49"
			},
			{
				"ebookId": 1489915669512,
				"title": "Sistemi za upravljanje digitalnim dokumentima",
				"author": "Dragan Ivanovićdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "02-sys-standards_19_03_2017_10_27_49.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: : <B>dokument</B> je i zaklju£ak sa sednice\r\nVlade, ili izve²taj komisije o izboru u nastavno zvanje ... ",
				"$$hashKey": "object:50"
			},
			{
				"ebookId": 1489915649298,
				"title": "Uvod u Upravljanje digitalnim dokumentima",
				"author": "Dragan Ivanovićdragan.ivanovic@uns.ac.rs",
				"keywords": "",
				"publicationYear": 1999,
				"filename": "01-intro_19_03_2017_10_27_29.pdf",
				"mime": "application/pdf",
				"language": {
					"languageId": 1,
					"name": "English"
				},
				"category": {
					"categoryId": 1,
					"name": "Fiction"
				},
				"highlight": "text: <B>dokument</B>\r\nautor\r\nnaslov\r\ndatum nastanka\r\nklju£ne re£i\r\nPrimer 2: metapodaci za fotograﬁju\r\nautor\r\ndatum i ... ",
				"$$hashKey": "object:51"
			}
			];

			return this.results;

		}


	}  

})(); 