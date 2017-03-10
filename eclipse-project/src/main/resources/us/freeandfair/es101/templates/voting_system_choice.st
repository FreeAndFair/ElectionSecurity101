voting_system_choice(voting_systems) ::= <<
<div align="center">
<table>
$voting_systems:{ voting_system |
  <tr><td class="system_button">
      <button type="button" name="$voting_system.schema$" class="voting_system"
              onclick="window.location.href='$voting_system.schema$'">$voting_system.name$</button></td>
      <td class="system_desc">$voting_system.usageRegions$</td></tr> 
}$
</table>
</div>
>>