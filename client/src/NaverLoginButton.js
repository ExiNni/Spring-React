import React from 'react';
import axios from 'axios';

export default function NaverLoginButton() {
    const handleNaverLogin = async () => {
        try {
            const response = await axios.get(
                'http://localhost:3000/login/oauth2/code/naver',
                { withCredentials: true }
            );
            console.log('FrontEnd에서 제공되는 콘솔 로그');
            console.log(response.data);
        } catch (error) {
            console.error('Naver login error : ', error);
        }
    };

    return (
        <div>
            <button onClick={handleNaverLogin}>Naver Login</button>
        </div>
    );
}
