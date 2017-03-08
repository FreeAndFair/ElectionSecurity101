manipulation_choice(election, button_group) ::= <<
<select name="$button_group$">
$election.candidates:{ candidate |
  <option value="$candidate$">$candidate$</option>
 }$
</select>
>>
