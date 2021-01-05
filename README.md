# BottomNavigationSample
BottomNavigation 을 통해서 프레그먼트를 교체할때 기존에 보던것을 계속해서 유지하도록 하기 위해 샘플을 작성

## Branch 별로 샘플 구성

### manual-chnage-fragment
예전에 사용하던 방식으로 프레그먼트 매니저를 사용해서 개발자가 직접 프레그먼트를 add, hide 하는 방식

### navigation-advanced-sample
(Android Architecture Components Advanced Navigation Sample)[https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample]
샘플을 보고 구현한 방식

**특징**
- NavigationView, Navigation Graph 를 사용해서 구현
- 내가 BottomNavigiation 을 사용해서 유지하려는 Fragment는 각각의 navigiation graph 를 따로 만들어서 관리
- NavigationExtension 에 네비게이션 교체 관련 기법이 구현되어 있음(LiveData 로 현재 선택된 navController 를 가져오고 setupActionBarWithNavController 를 연결한다.

**주의할점**
- menu, navigation 의 id, Activity의 navGraphIds 가 정확하게 일치해야지 작동(일치하지 않으면 저장해놓은 id에 맞는 데이터가 없어서 null 로 나오고 탭 변화가 없음)

