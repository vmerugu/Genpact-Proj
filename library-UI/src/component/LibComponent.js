import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import LibDataService from '../service/LibDataService';


class LibComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            location: '',
            name: ''
        }

        this.onSubmit = this.onSubmit.bind(this)
        this.validate = this.validate.bind(this)

    }

    

    validate(values) {
        let errors = {}
        if (!values.name) {
            errors.name = 'Enter a name'
        } else if (values.name.length < 5) {
            errors.name = 'Enter atleast 5 Characters in name'
        }
		

        return errors

    }

    onSubmit(values) {
		
        let libraries = {
            location: values.location,
            name: values.name
        }

        
            LibDataService.createLib(libraries)
                .then(() => this.props.history.push('/home'))

        console.log(values);
    }

    render() {

        let { location, name } = this.state

        return (
            <div>
                <h3>Add Library</h3>
                <div className="container">
                    <Formik
                        initialValues={{ location, name }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="name" component="div"
                                        className="alert alert-warning" />
                                    <fieldset className="form-group">
                                        <label>Location</label>
                                        <Field className="form-control" type="text" name="location" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Name</label>
                                        <Field className="form-control" type="text" name="name" />
                                    </fieldset>
                                    <button className="btn btn-success" type="submit" name="save">Save</button>
									
									
                                </Form>
                            )
                        }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default LibComponent