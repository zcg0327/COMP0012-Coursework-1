l1:seq<string> := ["a","b", "c", "d", "e"];
l2:seq<top H> := [1,2,3,4,5];
 
s1:string := "";
s2:string := "hello";

main {

  fnewlist:seq<top H> := l1 :: l2;
  anotherlist:seq<top H> := [s1] :: [s2] :: newlist;
  thirdlist:seq<top H> := l2[:-2] :: l2[3:];

  b:bool L := thirdlist.len = l2.len;


  if (newlist.len <= anotherlist.len) then
     newlist := newlist + anotherlist[0];
  else 
     nelist := newlist - newlist[0];
  fi
  
  return;
};
