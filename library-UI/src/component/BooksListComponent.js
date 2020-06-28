import React, { Component } from 'react'
import LibDataService from '../service/LibDataService.js';

class LibListComponent extends Component {
    constructor(props) {
        super(props)
		
        this.state = {
			id: this.props.match.params.id,
            books: [],
			message: null
			
			
        }
        
        this.refreshBooks = this.refreshBooks.bind(this)
		this.updateBookClicked = this.updateBookClicked.bind(this)
        this.addBookClicked = this.addBookClicked.bind(this)
      
    }

    componentDidMount() {
        this.refreshBooks();
			    }

    refreshBooks() {
        LibDataService.retrieveAllBooks(this.state.id)
	            .then(
                response => {
					this.setState({ books: response.data })
						
                }
            )
    }
	updateBookClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/booksMod/${id}`)
    }
	addBookClicked() {
		console.log('adding row')
        this.props.history.push(`/booksMod/-1`)
    }	

    render() {
        return (
            <div className="container">
				<br></br>
                <h3><center>List of Books</center></h3>
				<br></br>
				{this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
								<th>Library Id</th>
                                <th>Book Id</th>
                                <th>Author</th>
								<th>Subject</th>
								<th>Title</th>
								<th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.books.map(
                                    book =>
								            <tr key={book.id}>
											<td>{book.library.id}</td>
											<td>{book.id}</td>
                                            <td>{book.author}</td>
                                            <td>{book.subject}</td>
											<td>{book.title}</td>	
											<td><button className="btn btn-success" onClick={() => this.updateBookClicked(book.id)}>Update</button></td>
			
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
				<div className="row">
				<button className="btn btn-success" onClick={this.addBookClicked}>Add</button>
				
			</div>
            </div>
        )
    }
}

export default LibListComponent