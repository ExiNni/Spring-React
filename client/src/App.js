import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import AddProduct from './AddProduct';

const App = () => {
  const [products, setProducts] = useState([]);
  const [newProduct, setNewProduct] = useState({ name: '', price: '', description:'' });

  useEffect(() => {
    axios
      .get('http://localhost:8081/api/item')
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error(':', error);
      });
  }, []);

  const handleAddProduct = () => {
    axios
      .post('http://localhost:8081/api/add', newProduct)
      .then((response) => {
        setProducts((prevProducts) => [...prevProducts, response.data]);
        setNewProduct({ name: '', price: '', description:'' });
      })
      .catch((error) => {
        console.error('추가 에러:', error);
      });
  };

  const handleDeleteProduct = (id) => {
    axios
      .delete(`http://localhost:8081/api/delete/${id}`)
      .then((response) => {
        setProducts((prevProduct) =>
          //현재 목록에서 삭제할 제품을 제외하고
          //새로운 배열을 생성
          //삭제할 제품의 ID와 다른 제품들만 필터로 남겨주겠다
          //해주는 것
          prevProduct.filter((product) => product.id !== id)
        );
      })
      .catch((error) => {
        console.error('error', error);
      });
  };

  return (
    <Router>
      <div>
        <h1>상품 리스트</h1>
        <table border={1}>
          <thead>
            <tr>
              <th>name</th>
              <th>price</th>
              <th>description</th>
              <th>삭제</th>
              <th>수정</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.name}</td>
                <td>{product.price}</td>
                <td>{product.description}</td>
                <td>
                  <button onClick={() => handleDeleteProduct(product.id)}>
                    삭제
                  </button>
                </td>
                <td>
                  <button onClick={() => handleEditProduct(product)}>
                    수정하기
                  </button>
                </td>
              </tr>

              <h2>{isEditing ? '상품 수정' : '상품 추가'}</h2>
            ))}
          </tbody>
        </table>
      </div>
      <Routes>
        <Route path="/add" element={<AddProduct onAddProduct={handleAddProduct} />} />
      </Routes>
    </Router>
  );
}
export default App;