import React, { useState } from 'react';
import axios from 'axios';

function AddProduct({ onAddProduct }) {
    const [newProduct, setNewProduct] = useState({ name: '', price: 0 });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct((prevProduct) => ({ ...prevProduct, [name]: value }));
    };

    const handleAddProduct = async () => {
        try {
            const response = await axios.post(
                'http://localhost:8081/api/add',
                newProduct,
                { withCredentials: true }
            );
            onAddProduct((prevProducts) => [...prevProducts, response.data]);
            setNewProduct({ name: '', price: '' });
        } catch (error) {
            console.error('error:', error);
        }
    };




    return (
        <div>
            <h2>{isEditing ? '상품 수정 ' : '상품 추가'}</h2>
            <div>
                <label>이름:</label>
                <input
                    type="text"
                    name="name"
                    value={newProduct.name}
                    onChange={(e) => setNewProduct({ ...newProduct, name: e.target.value })}
                />
            </div>
            <div>
                <label>가격:</label>
                <input
                    type="number"
                    name="price"
                    value={newProduct.price}
                    onChange={(e) =>
                        setNewProduct({ ...newProduct, price: e.target.valueAsNumber })
                    }
                />
                {isEditing > (
                    <div>
                        <button onClick={handleEditProduct}>상품수정</button>
                        <button onClick={handleCancelProduct}>수정취소</button>
                    </div>
                )}
            </div>
            <button onClick={handleAddProduct}>상품등록</button>
        </div>
    );
}

export default AddProduct;