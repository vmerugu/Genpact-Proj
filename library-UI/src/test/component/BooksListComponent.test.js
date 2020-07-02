import React, { Component } from 'react'
import BooksListComponent from '../../component/BooksListComponent.js';
import { shallow } from 'enzyme'


const mockTryGetValue = jest.fn(() => false);
const mockTrySetValue = jest.fn(); 


describe("BooksListComponent", () => {
  it("should render book list component", () => {
    const wrapper = shallow(<BooksListComponent />);
	expect(wrapper).toMatchSnapshot();
  });
  it('should render a list with the body of each item inside a div', () => {
    const ArrayData = [{ Library Id: 1, Book Id: 11 ,Author:"John Peter",Subject: "Motivation" ,Title: "Self Motivation",Update : "Click" }, { Library Id: 2, Book Id: 12 ,Author:"Peterson",Subject: "React Js" ,Title: "Mobile App",Update : "Click"  }]
    const wrapper = shallow(<List items={ArrayData} />)
    const items = wrapper.find('div[class="container"]')
    expect(items).toHaveLength(ArrayData.length)
    expect(items.first().text()).toEqual('John Peter')
  });
  it('should set storage on Add button click', () => {
    mockTryGetValue.mockReturnValueOnce(true);
    const component = mount(<BooksListComponent />); 
    component.find('button#btn btn-success').simulate('click');
    expect(mockTryGetValue).toHaveBeenCalled();
    expect(component).toMatchSnapshot();
    component.unmount();   
  });    
});



