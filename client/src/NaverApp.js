import React from 'react';
import NaverLogin from 'react-naver-login';
//npm install react-naver-login
const NaverApp = () => {
    const clientId = 'HQHp_3R0uDH7Ey5eoKgv';
    const NaverLoginSuccess = (response) => {
        console.log(response);
    };
    const NaverLoginFailure = (err) => {
        console.error(err);
    };
    return (
        <NaverLogin
            clientId={clientId}
            callbackUrl="http://localhost:3000/login/oauth2/code/naver"
            onSuccess={NaverLoginSuccess}
            onFailure={NaverLoginFailure}
            render={(props) => (
                <button onClick={props.onClick}>네이버로 로그인</button>
            )}
        />
    );
};
export default NaverApp;