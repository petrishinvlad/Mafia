import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';
// import renderer from 'react-test-renderer';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
  
  
});

// it('renders all properties', () => {
//   const component = renderer.create(<Item item={item} />);

// expect(component.root.findByType('a').props.href).toEqual(
//   'https://reactjs.org/'
// );
// expect(
//   component.root.findAllByType('span')[1].props.children
// ).toEqual('Jordan Walke');
// expect(
//   component.root.findAllByProps({ children: 'Jordan Walke' })
//     .length
// ).toEqual(1);


// const handleRemoveItem = jest.fn();

//     const component = renderer.create(
//       <Item item={item} onRemoveItem={handleRemoveItem} />
//     );

//     component.root.findByType('button').props.onClick();

//     expect(handleRemoveItem).toHaveBeenCalledTimes(1);
//     expect(handleRemoveItem).toHaveBeenCalledWith(item);

//     expect(component.root.findAllByType(Item).length).toEqual(1);


// });
