import React from 'react';
import KakaoLogin from 'react-kakao-login';

const KakaoApp = () => {
    const KakaoLoginSuccess = (res) => {
        console.log(res);
    };
    const KakaoLoginFailure = (err) => {
        console.error(err);
    };

    return (
        <div>
            <KakaoLogin
                token=""
                onSuccess={KakaoLoginSuccess}
                onFailure={KakaoLoginFailure}
                //getProfile={true}
                render={(props) => (
                    <button onClick={props.onClick}>카카오로 로그인</button>
                )}
            />
        </div>
    );
};
export default KakaoApp;