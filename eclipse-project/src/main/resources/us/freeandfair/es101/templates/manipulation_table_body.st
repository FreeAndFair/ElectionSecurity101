manipulation_table_body(election, button_group) ::= <<
$election.candidates:{ candidate |
  <tr><td align="center"><input type="radio" name="$button_group$" value="$candidate$">&nbsp; $candidate$</input></td></tr>
 }$
>>
