import React, { Component } from 'react'
import LibListComponent from '../../component/LibListComponent.js';
import { shallow } from 'enzyme'


const mockTryGetValue = jest.fn(() => false);
const mockTrySetValue = jest.fn(); 


describe("LibListComponent", () => {
  it("should render libriary list component", () => {
    const wrapper = shallow(<LibListComponent />);
	expect(wrapper).toMatchSnapshot();
  });
  it('should render a list with the body of each item inside a div', () => {
    const ArrayData = [{ id: 1, Name: 'Bangalore Lib',Location:"MG Road",BookDetails : "Click" }, { id: 2, Name: 'Bangalore Lib2',Location:"KR Road",BookDetails : "Click"  }]
    const wrapper = shallow(<List items={ArrayData} />)
    const items = wrapper.find('div[class="container"]')
    expect(items).toHaveLength(ArrayData.length)
    expect(items.first().text()).toEqual('Bangalore Lib')
  });
  it('should set storage on Add button click', () => {
    mockTryGetValue.mockReturnValueOnce(true);
    const component = mount(<LibListComponent />); 
    component.find('button#btn btn-success').simulate('click');
    expect(mockTryGetValue).toHaveBeenCalled();
    expect(component).toMatchSnapshot();
    component.unmount();   
  });    
});


           