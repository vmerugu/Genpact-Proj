
import React, { Component } from 'react'
import BooksComponent from '../../component/BooksComponent.js';
import { shallow } from 'enzyme'


const mockTryGetValue = jest.fn(() => false);
const mockTrySetValue = jest.fn(); 


describe("BooksComponent", () => {
  it("should render book list component", () => {
    const wrapper = shallow(<BooksComponent />);
	expect(wrapper).toMatchSnapshot();
  });
  it('should render a list with the body of each item inside a div', () => {
    const ArrayData = [{ Book Id: 11 ,Library Id: 1, Author:"John Peter",Subject: "Motivation" ,Title: "Self Motivation",Save : "Click" }, { Book Id: 12 ,Library Id: 2, Author:"Peterson",Subject: "React Js" ,Title: "Mobile App",Save : "Click"  }]
    const wrapper = shallow(<List items={ArrayData} />)
    const items = wrapper.find('div[class="container"]')
    expect(items).toHaveLength(ArrayData.length)
    expect(items.first().text()).toEqual('John Peter')
  });
  it('should set storage on Add button click', () => {
    mockTryGetValue.mockReturnValueOnce(true);
    const component = mount(<BooksComponent />); 
    component.find('button#btn btn-success').simulate('click');
    expect(mockTryGetValue).toHaveBeenCalled();
    expect(component).toMatchSnapshot();
    component.unmount();   
  });    
});



