import React, { useState } from 'react';
import axios from 'axios';
function AddProduct({ onAddProduct }) {
    const [newProduct, setNewProduct] = useState({ name: '', price: '', description: '' });
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
    };
    const handleAddProduct = async () => {
        
    };
    return (
        <div>
            <h2>새로운 제품 추가</h2>
            <div>
                <label>이름:</label>
                <input
                    type="text"
                    name="name"
                    value={newProduct.name}
                    onChange={handleInputChange}
                />
            </div>
            <div>
                <label>가격:</label>
                <input
                    type="text"
                    name="price"
                    value={newProduct.price}
                    onChange={handleInputChange}
                />
            </div>
            <div>
                <label>설명:</label>
                <input
                    type="text"
                    name="description"
                    value={newProduct.description}
                    onChange={handleInputChange}
                />
            </div>
            <button onClick={handleAddProduct}>제품 추가</button>
        </div>
    );
}
export default AddProduct;