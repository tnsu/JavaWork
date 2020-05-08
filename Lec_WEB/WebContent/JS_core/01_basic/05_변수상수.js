// vat , let, const

// var 를 사용한 변수 선언
var a = 100;
console.log('a=', a);
a= 200;
console.log('a=', a);

var a = 500; // 중복 선언 가능
console.log('a=', a);

//ES6 부터 let, const 로 변수 상수 지정

// let 를 사용한 변수 선언
let value = 177;
console.log('value=', value);
value = 555;
console.log('value=', value);

//let value = 1; //  중복선언이 불가능. 동일 이름의 변수 let으로 선언 불가 

// 상수 선언 Java : final
const b = 1;
console.log('b=',b);
// b=2; // 오류



//-----------------------------------
/* var는 오늘날 JS 환경에서는 그닥 권장하진 않는다. (사용은 가능)

 IE9, IE10  와 같은 구형 브라우저에서는 let, const 를 사용 못함.

 개발 단계에서는 '바벨' 등을 사용하여
 우리가 개발한 코드가 구형 브라우저 에서도 동작케 함.

*/

// 초기화를 따로 해도 가능.
let c;
c=100;


// ` : back tick
console.log(`c= ${c}`); //  Template Literal(ES6 이상(<=) )

// 데이터 타입들
let value2 = 100; // number type


console.log(value2, typeof value2); // typeof 연산자 : 변수의 타입
console.log(value2, typeof(value2)); // 동작은되나 위의 방향으로 사용함 (함수같은 느낌ㅋ)

//JS 은 대입되는 값에 따라 '타입' 바뀜
value2 = 'hello'; // String 타입
console.log(value2, typeof value2);
value2 = "Hello"; // '' "" 둘다 가능



