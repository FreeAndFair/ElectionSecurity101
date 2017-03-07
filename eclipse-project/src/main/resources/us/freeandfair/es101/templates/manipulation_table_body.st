manipulation_table_body(election, group) ::= <<
$election.candidates:{ candidate |
  <tr><td><input type="radio" name="$group$" value="$candidate$">$candidate$</input></td></tr>
 }$
>>