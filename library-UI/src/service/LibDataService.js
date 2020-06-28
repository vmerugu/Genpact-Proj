import axios from 'axios'

const MODULE1 = 'library'
const MODULE2 = 'book/ofLibrary?'
const MODULE3 = 'book/update?'
const MODULE4 = 'book/add'


const API_URL = 'http://localhost:8096'
const BE_API_URL1 = `${API_URL}/api/v1/${MODULE1}`
const BE_API_URL2 = `${API_URL}/api/v1/${MODULE2}`
const BE_API_URL3 = `${API_URL}/api/v1/${MODULE3}`
const BE_API_URL4 = `${API_URL}/api/v1/${MODULE4}`

class LibDataService {

    retrieveAllLibraries() {
        return axios.get(`${BE_API_URL1}`);
    }
	createLib(libraries) {
        return axios.post(`${BE_API_URL1}/`,libraries);
    }
	retrieveAllBooks(id)
	{
		return axios.get(`${BE_API_URL2}libraryId=${id}`);
	}
	updateBook(id,author,subject,title) {
		
		console.log('executed service')
        return axios.put(`${BE_API_URL3}author=${author}&id=${id}&subject=${subject}&title=${title}`);
		
    }
	createBook(book) {
		
        return axios.post(`${BE_API_URL4}`,book);
    }
}

export default new LibDataService()