import React, { Component } from 'react';
import LibListComponent from './LibListComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import LibComponent from './LibComponent';
import BooksListComponent from './BooksListComponent';
import BookComponent from './BookComponent';


class RouterApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <h1>Genpact Application</h1>
                    <Switch>
                        <Route path="/" exact component={LibListComponent} />
						<Route path="/home" exact component={LibListComponent} />
						<Route path="/libraries" exact component={LibComponent} />
						<Route path="/books/:id" exact component={BooksListComponent} />
						<Route path="/booksMod/:id" exact component={BookComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}

export default RouterApp