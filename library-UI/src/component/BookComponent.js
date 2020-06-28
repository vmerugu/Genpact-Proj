import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import LibDataService from '../service/LibDataService';


class BookComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            author: '',
			id: this.props.match.params.id,
            subject: '',
			title: '',
			libraryid: '',
			isIssued: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    

    validate(values) {
        let errors = {}
        if (!values.author) {
            errors.author = 'Enter a author'
        } else if (values.author.length < 5) {
            errors.author = 'Enter atleast 5 Characters in author'
        }

        return errors

    }

    onSubmit(values) {

        let book = {
            id: this.state.id,
            author: values.author,
			subject: values.subject,
			title: values.title,
			libraryId : values.libraryid,
			isIssued:true
        }
		
        if (this.state.id == -1) {
            LibDataService.createBook(book)
                .then(() => this.props.history.push(`/books/${values.libraryid}`))
        } else {
            LibDataService.updateBook(this.state.id,values.author,values.subject,values.title)
                .then(() => this.props.history.push(`/books/${values.libraryid}`))
        }

        console.log(book);
    }

    render() {

        let { title,author,subject,libraryid, id } = this.state

        return (
            <div>
                <h3>Book Details</h3>
                <div className="container">
                    <Formik
                        initialValues={{ title,author,subject,libraryid, id }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="author" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Book Id</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset>
									<fieldset className="form-group">
                                        <label>Library Id</label>
                                        <Field className="form-control" type="text" name="libraryid"  />
                                    </fieldset>
									<fieldset className="form-group">
                                        <label>Author</label>
                                        <Field className="form-control" type="text" name="author" />
                                    </fieldset>
									<fieldset className="form-group">
                                        <label>Subject</label>
                                        <Field className="form-control" type="text" name="subject" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Title</label>
                                        <Field className="form-control" type="text" name="title" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit">Save</button>
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default BookComponent