import React, { Component } from 'react'
import LibComponent from '../../component/LibComponent.js';
import { shallow } from 'enzyme'


const mockTryGetValue = jest.fn(() => false);
const mockTrySetValue = jest.fn(); 


describe("LibComponent", () => {
  it("should render libriary list component", () => {
    const wrapper = shallow(<LibComponent />);
	expect(wrapper).toMatchSnapshot();
  });
  it('should render a list with the body of each item inside a div', () => {
    const ArrayData = [{ Location: "MG Road", Name:"Bangalore Library1"}, { Location: "KR Puram Road", Name:"Bangalore Library2" }]
    const wrapper = shallow(<List items={ArrayData} />)
    const items = wrapper.find('div[class="container"]')
    expect(items).toHaveLength(ArrayData.length)
    expect(items.first().text()).toEqual('Bangalore Library1')
  });
  it('should set storage on Add button click', () => {
    mockTryGetValue.mockReturnValueOnce(true);
    const component = mount(<LibComponent />); 
    component.find('button#btn btn-success').simulate('click');
    expect(mockTryGetValue).toHaveBeenCalled();
    expect(component).toMatchSnapshot();
    component.unmount();   
  });    
});





















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