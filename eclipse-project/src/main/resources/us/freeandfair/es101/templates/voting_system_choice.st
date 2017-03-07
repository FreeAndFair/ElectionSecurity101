voting_system_choice(voting_systems) ::= <<
<div align="center">
<table border=0>
<tr><th colspan="2">Choose a Voting System</b></th></tr>
$voting_systems:{ voting_system |
  <tr><td><a href="$voting_system.schema$">$voting_system.name$</a></td>
      <td>$voting_system.usageRegions$</td></tr> 
 }$
</table>
</div>
>>