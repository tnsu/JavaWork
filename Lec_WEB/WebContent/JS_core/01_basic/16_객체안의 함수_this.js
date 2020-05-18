const dog = {
    name :'댕댕스',
    sound :'멍멍',

    // 객체 안의 함수 정의하는 다양한 방법들

    // 방법1
    say1 : function aaa(){
        console.log(this.sound); // this는 함수가 위치한(소유한) 객체
    }
    ,
    // 방법2
    say2 : function(){
        console.log(this.sound);
    }
    ,
    // 방법3 : 최신 JS 방식(추천 ) (..이지만 브라우저에서는 사용하지말자)
    say3(){
        console.log(this.sound);
    }
    ,
    // 화살표 함수의 경우.. this 가 문제 된다.
    say4: () => {
        console.log(this.sound);
        console.log(this);
    } 
      // 이유
  // function 키워드로 만든 함수에서의 this는
  // 자기가 속해있는, 객체를 가리키는데..
  // 화살표 함수의 경우
  // this를 자기가 속해 있는 곳으로 연결하지 않습니다.
  // 화살표 함수는 this 가 뭔지 모릅니다!
}

dog.say1();
// dog.aaa(); 
dog.say2();
dog.say3();

dog.say4(); //undefined


const cat = {
    name : "야옹이",
    sound : "냐아~"
}
//cat.say1();
cat.say1 = dog.say1;


dog.say1();
cat.say1();   // 이때 this 는 cat 이 된다.

const sayCat = cat.say1;
console.log(typeof sayCat); // function
sayCat();  // undefined  this에 아무것도 연결되지 않는다.
 






















