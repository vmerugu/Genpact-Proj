import React, { Component } from 'react'
import LibDataService from '../service/LibDataService.js';

class LibListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            libraries: [],
			message: null
        }
        this.refreshLibrary = this.refreshLibrary.bind(this)
		this.addLibClicked = this.addLibClicked.bind(this)
    }

    componentDidMount() {
        this.refreshLibrary();
			    }
	addLibClicked() {
    this.props.history.push(`/libraries`)
}
    refreshLibrary() {
        LibDataService.retrieveAllLibraries()
	            .then(
                response => {
					this.setState({ libraries: response.data })
						
                }
            )
    }
    getBooksClicked(id) {
        console.log('Library Id ' + id)
        this.props.history.push(`/books/${id}`)
    }

    render() {
        return (
            <div className="container">
				<br></br>
                <h3><center>List of Libraries in Bangalore</center></h3>
				<br></br>
				{this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
								<th>Location</th>
								<th>Book Details</th>
									
					
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.libraries.map(
                                    lib =>
								            <tr key={lib.id}>
											<td>{lib.id}</td>
                                            <td>{lib.name}</td>
                                            <td>{lib.location}</td>
											<td><button className="btn btn-success" onClick={() => this.getBooksClicked(lib.id)}>Books</button></td>										
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
				<div className="row">
				<button className="btn btn-success" onClick={this.addLibClicked}>Add</button>
			</div>
            </div>
        )
    }
}

export default LibListComponent